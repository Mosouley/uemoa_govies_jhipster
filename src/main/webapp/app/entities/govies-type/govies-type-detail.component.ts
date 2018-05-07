import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { GoviesType } from './govies-type.model';
import { GoviesTypeService } from './govies-type.service';

@Component({
    selector: 'jhi-govies-type-detail',
    templateUrl: './govies-type-detail.component.html'
})
export class GoviesTypeDetailComponent implements OnInit, OnDestroy {

    goviesType: GoviesType;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private goviesTypeService: GoviesTypeService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInGoviesTypes();
    }

    load(id) {
        this.goviesTypeService.find(id)
            .subscribe((goviesTypeResponse: HttpResponse<GoviesType>) => {
                this.goviesType = goviesTypeResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInGoviesTypes() {
        this.eventSubscriber = this.eventManager.subscribe(
            'goviesTypeListModification',
            (response) => this.load(this.goviesType.id)
        );
    }
}
