import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { GoviesTypeComponent } from './govies-type.component';
import { GoviesTypeDetailComponent } from './govies-type-detail.component';
import { GoviesTypePopupComponent } from './govies-type-dialog.component';
import { GoviesTypeDeletePopupComponent } from './govies-type-delete-dialog.component';

export const goviesTypeRoute: Routes = [
    {
        path: 'govies-type',
        component: GoviesTypeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhispterApp.goviesType.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'govies-type/:id',
        component: GoviesTypeDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhispterApp.goviesType.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const goviesTypePopupRoute: Routes = [
    {
        path: 'govies-type-new',
        component: GoviesTypePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhispterApp.goviesType.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'govies-type/:id/edit',
        component: GoviesTypePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhispterApp.goviesType.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'govies-type/:id/delete',
        component: GoviesTypeDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhispterApp.goviesType.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
