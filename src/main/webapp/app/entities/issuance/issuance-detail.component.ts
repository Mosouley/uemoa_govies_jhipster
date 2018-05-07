import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Issuance } from './issuance.model';
import { IssuanceService } from './issuance.service';

@Component({
    selector: 'jhi-issuance-detail',
    templateUrl: './issuance-detail.component.html'
})
export class IssuanceDetailComponent implements OnInit, OnDestroy {

    issuance: Issuance;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private issuanceService: IssuanceService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInIssuances();
    }

    load(id) {
        this.issuanceService.find(id)
            .subscribe((issuanceResponse: HttpResponse<Issuance>) => {
                this.issuance = issuanceResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInIssuances() {
        this.eventSubscriber = this.eventManager.subscribe(
            'issuanceListModification',
            (response) => this.load(this.issuance.id)
        );
    }
}
