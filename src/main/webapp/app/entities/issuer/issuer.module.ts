import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhispterSharedModule } from '../../shared';
import {
    IssuerService,
    IssuerPopupService,
    IssuerComponent,
    IssuerDetailComponent,
    IssuerDialogComponent,
    IssuerPopupComponent,
    IssuerDeletePopupComponent,
    IssuerDeleteDialogComponent,
    issuerRoute,
    issuerPopupRoute,
    IssuerResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...issuerRoute,
    ...issuerPopupRoute,
];

@NgModule({
    imports: [
        JhispterSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        IssuerComponent,
        IssuerDetailComponent,
        IssuerDialogComponent,
        IssuerDeleteDialogComponent,
        IssuerPopupComponent,
        IssuerDeletePopupComponent,
    ],
    entryComponents: [
        IssuerComponent,
        IssuerDialogComponent,
        IssuerPopupComponent,
        IssuerDeleteDialogComponent,
        IssuerDeletePopupComponent,
    ],
    providers: [
        IssuerService,
        IssuerPopupService,
        IssuerResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhispterIssuerModule {}
