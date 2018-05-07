import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { GoviesType } from './govies-type.model';
import { GoviesTypePopupService } from './govies-type-popup.service';
import { GoviesTypeService } from './govies-type.service';

@Component({
    selector: 'jhi-govies-type-dialog',
    templateUrl: './govies-type-dialog.component.html'
})
export class GoviesTypeDialogComponent implements OnInit {

    goviesType: GoviesType;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private goviesTypeService: GoviesTypeService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.goviesType.id !== undefined) {
            this.subscribeToSaveResponse(
                this.goviesTypeService.update(this.goviesType));
        } else {
            this.subscribeToSaveResponse(
                this.goviesTypeService.create(this.goviesType));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<GoviesType>>) {
        result.subscribe((res: HttpResponse<GoviesType>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: GoviesType) {
        this.eventManager.broadcast({ name: 'goviesTypeListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-govies-type-popup',
    template: ''
})
export class GoviesTypePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private goviesTypePopupService: GoviesTypePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.goviesTypePopupService
                    .open(GoviesTypeDialogComponent as Component, params['id']);
            } else {
                this.goviesTypePopupService
                    .open(GoviesTypeDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
