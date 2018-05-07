import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhispterSharedModule } from '../../shared';
import {
    IssuanceService,
    IssuancePopupService,
    IssuanceComponent,
    IssuanceDetailComponent,
    IssuanceDialogComponent,
    IssuancePopupComponent,
    IssuanceDeletePopupComponent,
    IssuanceDeleteDialogComponent,
    issuanceRoute,
    issuancePopupRoute,
} from './';

const ENTITY_STATES = [
    ...issuanceRoute,
    ...issuancePopupRoute,
];

@NgModule({
    imports: [
        JhispterSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        IssuanceComponent,
        IssuanceDetailComponent,
        IssuanceDialogComponent,
        IssuanceDeleteDialogComponent,
        IssuancePopupComponent,
        IssuanceDeletePopupComponent,
    ],
    entryComponents: [
        IssuanceComponent,
        IssuanceDialogComponent,
        IssuancePopupComponent,
        IssuanceDeleteDialogComponent,
        IssuanceDeletePopupComponent,
    ],
    providers: [
        IssuanceService,
        IssuancePopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhispterIssuanceModule {}
