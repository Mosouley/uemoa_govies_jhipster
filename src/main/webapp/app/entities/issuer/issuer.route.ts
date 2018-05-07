import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { IssuerComponent } from './issuer.component';
import { IssuerDetailComponent } from './issuer-detail.component';
import { IssuerPopupComponent } from './issuer-dialog.component';
import { IssuerDeletePopupComponent } from './issuer-delete-dialog.component';

@Injectable()
export class IssuerResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const issuerRoute: Routes = [
    {
        path: 'issuer',
        component: IssuerComponent,
        resolve: {
            'pagingParams': IssuerResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhispterApp.issuer.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'issuer/:id',
        component: IssuerDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhispterApp.issuer.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const issuerPopupRoute: Routes = [
    {
        path: 'issuer-new',
        component: IssuerPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhispterApp.issuer.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'issuer/:id/edit',
        component: IssuerPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhispterApp.issuer.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'issuer/:id/delete',
        component: IssuerDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhispterApp.issuer.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
