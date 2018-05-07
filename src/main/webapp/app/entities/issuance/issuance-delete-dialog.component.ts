import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Issuance } from './issuance.model';
import { IssuancePopupService } from './issuance-popup.service';
import { IssuanceService } from './issuance.service';

@Component({
    selector: 'jhi-issuance-delete-dialog',
    templateUrl: './issuance-delete-dialog.component.html'
})
export class IssuanceDeleteDialogComponent {

    issuance: Issuance;

    constructor(
        private issuanceService: IssuanceService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.issuanceService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'issuanceListModification',
                content: 'Deleted an issuance'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-issuance-delete-popup',
    template: ''
})
export class IssuanceDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private issuancePopupService: IssuancePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.issuancePopupService
                .open(IssuanceDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
