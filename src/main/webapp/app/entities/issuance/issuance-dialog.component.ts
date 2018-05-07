import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Issuance } from './issuance.model';
import { IssuancePopupService } from './issuance-popup.service';
import { IssuanceService } from './issuance.service';
import { Issuer, IssuerService } from '../issuer';
import { GoviesType, GoviesTypeService } from '../govies-type';

@Component({
    selector: 'jhi-issuance-dialog',
    templateUrl: './issuance-dialog.component.html'
})
export class IssuanceDialogComponent implements OnInit {

    issuance: Issuance;
    isSaving: boolean;

    issuers: Issuer[];

    goviestypes: GoviesType[];
    issueDateDp: any;
    valueDateDp: any;
    maturityDateDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private issuanceService: IssuanceService,
        private issuerService: IssuerService,
        private goviesTypeService: GoviesTypeService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.issuerService.query()
            .subscribe((res: HttpResponse<Issuer[]>) => { this.issuers = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.goviesTypeService.query()
            .subscribe((res: HttpResponse<GoviesType[]>) => { this.goviestypes = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.issuance.id !== undefined) {
            this.subscribeToSaveResponse(
                this.issuanceService.update(this.issuance));
        } else {
            this.subscribeToSaveResponse(
                this.issuanceService.create(this.issuance));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Issuance>>) {
        result.subscribe((res: HttpResponse<Issuance>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Issuance) {
        this.eventManager.broadcast({ name: 'issuanceListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackIssuerById(index: number, item: Issuer) {
        return item.id;
    }

    trackGoviesTypeById(index: number, item: GoviesType) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-issuance-popup',
    template: ''
})
export class IssuancePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private issuancePopupService: IssuancePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.issuancePopupService
                    .open(IssuanceDialogComponent as Component, params['id']);
            } else {
                this.issuancePopupService
                    .open(IssuanceDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
