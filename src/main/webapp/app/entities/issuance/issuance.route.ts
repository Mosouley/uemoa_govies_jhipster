import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { IssuanceComponent } from './issuance.component';
import { IssuanceDetailComponent } from './issuance-detail.component';
import { IssuancePopupComponent } from './issuance-dialog.component';
import { IssuanceDeletePopupComponent } from './issuance-delete-dialog.component';

export const issuanceRoute: Routes = [
    {
        path: 'issuance',
        component: IssuanceComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhispterApp.issuance.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'issuance/:id',
        component: IssuanceDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhispterApp.issuance.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const issuancePopupRoute: Routes = [
    {
        path: 'issuance-new',
        component: IssuancePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhispterApp.issuance.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'issuance/:id/edit',
        component: IssuancePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhispterApp.issuance.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'issuance/:id/delete',
        component: IssuanceDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhispterApp.issuance.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
