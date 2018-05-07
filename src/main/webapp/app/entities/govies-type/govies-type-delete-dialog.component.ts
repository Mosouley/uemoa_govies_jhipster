import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { GoviesType } from './govies-type.model';
import { GoviesTypePopupService } from './govies-type-popup.service';
import { GoviesTypeService } from './govies-type.service';

@Component({
    selector: 'jhi-govies-type-delete-dialog',
    templateUrl: './govies-type-delete-dialog.component.html'
})
export class GoviesTypeDeleteDialogComponent {

    goviesType: GoviesType;

    constructor(
        private goviesTypeService: GoviesTypeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.goviesTypeService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'goviesTypeListModification',
                content: 'Deleted an goviesType'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-govies-type-delete-popup',
    template: ''
})
export class GoviesTypeDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private goviesTypePopupService: GoviesTypePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.goviesTypePopupService
                .open(GoviesTypeDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
