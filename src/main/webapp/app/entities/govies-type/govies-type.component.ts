import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { GoviesType } from './govies-type.model';
import { GoviesTypeService } from './govies-type.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-govies-type',
    templateUrl: './govies-type.component.html'
})
export class GoviesTypeComponent implements OnInit, OnDestroy {
goviesTypes: GoviesType[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private goviesTypeService: GoviesTypeService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.goviesTypeService.query().subscribe(
            (res: HttpResponse<GoviesType[]>) => {
                this.goviesTypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInGoviesTypes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: GoviesType) {
        return item.id;
    }
    registerChangeInGoviesTypes() {
        this.eventSubscriber = this.eventManager.subscribe('goviesTypeListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
