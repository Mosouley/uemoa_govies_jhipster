import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Issuer } from './issuer.model';
import { IssuerPopupService } from './issuer-popup.service';
import { IssuerService } from './issuer.service';

@Component({
    selector: 'jhi-issuer-dialog',
    templateUrl: './issuer-dialog.component.html'
})
export class IssuerDialogComponent implements OnInit {

    issuer: Issuer;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private issuerService: IssuerService,
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
        if (this.issuer.id !== undefined) {
            this.subscribeToSaveResponse(
                this.issuerService.update(this.issuer));
        } else {
            this.subscribeToSaveResponse(
                this.issuerService.create(this.issuer));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Issuer>>) {
        result.subscribe((res: HttpResponse<Issuer>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Issuer) {
        this.eventManager.broadcast({ name: 'issuerListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-issuer-popup',
    template: ''
})
export class IssuerPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private issuerPopupService: IssuerPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.issuerPopupService
                    .open(IssuerDialogComponent as Component, params['id']);
            } else {
                this.issuerPopupService
                    .open(IssuerDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
