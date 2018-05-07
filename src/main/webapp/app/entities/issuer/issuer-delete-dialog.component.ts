import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Issuer } from './issuer.model';
import { IssuerPopupService } from './issuer-popup.service';
import { IssuerService } from './issuer.service';

@Component({
    selector: 'jhi-issuer-delete-dialog',
    templateUrl: './issuer-delete-dialog.component.html'
})
export class IssuerDeleteDialogComponent {

    issuer: Issuer;

    constructor(
        private issuerService: IssuerService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.issuerService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'issuerListModification',
                content: 'Deleted an issuer'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-issuer-delete-popup',
    template: ''
})
export class IssuerDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private issuerPopupService: IssuerPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.issuerPopupService
                .open(IssuerDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
