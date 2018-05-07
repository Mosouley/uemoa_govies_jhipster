import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { JhispterIssuanceModule } from './issuance/issuance.module';
import { JhispterGoviesTypeModule } from './govies-type/govies-type.module';
import { JhispterIssuerModule } from './issuer/issuer.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [

        JhispterIssuanceModule,
        JhispterGoviesTypeModule,
        JhispterIssuerModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhispterEntityModule {}
