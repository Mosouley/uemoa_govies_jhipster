import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Issuer } from './issuer.model';
import { IssuerService } from './issuer.service';

@Component({
    selector: 'jhi-issuer-detail',
    templateUrl: './issuer-detail.component.html'
})
export class IssuerDetailComponent implements OnInit, OnDestroy {

    issuer: Issuer;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private issuerService: IssuerService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInIssuers();
    }

    load(id) {
        this.issuerService.find(id)
            .subscribe((issuerResponse: HttpResponse<Issuer>) => {
                this.issuer = issuerResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInIssuers() {
        this.eventSubscriber = this.eventManager.subscribe(
            'issuerListModification',
            (response) => this.load(this.issuer.id)
        );
    }
}
