import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhispterSharedModule } from '../../shared';
import {
    GoviesTypeService,
    GoviesTypePopupService,
    GoviesTypeComponent,
    GoviesTypeDetailComponent,
    GoviesTypeDialogComponent,
    GoviesTypePopupComponent,
    GoviesTypeDeletePopupComponent,
    GoviesTypeDeleteDialogComponent,
    goviesTypeRoute,
    goviesTypePopupRoute,
} from './';

const ENTITY_STATES = [
    ...goviesTypeRoute,
    ...goviesTypePopupRoute,
];

@NgModule({
    imports: [
        JhispterSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        GoviesTypeComponent,
        GoviesTypeDetailComponent,
        GoviesTypeDialogComponent,
        GoviesTypeDeleteDialogComponent,
        GoviesTypePopupComponent,
        GoviesTypeDeletePopupComponent,
    ],
    entryComponents: [
        GoviesTypeComponent,
        GoviesTypeDialogComponent,
        GoviesTypePopupComponent,
        GoviesTypeDeleteDialogComponent,
        GoviesTypeDeletePopupComponent,
    ],
    providers: [
        GoviesTypeService,
        GoviesTypePopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhispterGoviesTypeModule {}
