import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Issuance } from './issuance.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Issuance>;

@Injectable()
export class IssuanceService {

    private resourceUrl =  SERVER_API_URL + 'api/issuances';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(issuance: Issuance): Observable<EntityResponseType> {
        const copy = this.convert(issuance);
        return this.http.post<Issuance>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(issuance: Issuance): Observable<EntityResponseType> {
        const copy = this.convert(issuance);
        return this.http.put<Issuance>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Issuance>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Issuance[]>> {
        const options = createRequestOption(req);
        return this.http.get<Issuance[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Issuance[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Issuance = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Issuance[]>): HttpResponse<Issuance[]> {
        const jsonResponse: Issuance[] = res.body;
        const body: Issuance[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Issuance.
     */
    private convertItemFromServer(issuance: Issuance): Issuance {
        const copy: Issuance = Object.assign({}, issuance);
        copy.issueDate = this.dateUtils
            .convertLocalDateFromServer(issuance.issueDate);
        copy.valueDate = this.dateUtils
            .convertLocalDateFromServer(issuance.valueDate);
        copy.maturityDate = this.dateUtils
            .convertLocalDateFromServer(issuance.maturityDate);
        return copy;
    }

    /**
     * Convert a Issuance to a JSON which can be sent to the server.
     */
    private convert(issuance: Issuance): Issuance {
        const copy: Issuance = Object.assign({}, issuance);
        copy.issueDate = this.dateUtils
            .convertLocalDateToServer(issuance.issueDate);
        copy.valueDate = this.dateUtils
            .convertLocalDateToServer(issuance.valueDate);
        copy.maturityDate = this.dateUtils
            .convertLocalDateToServer(issuance.maturityDate);
        return copy;
    }
}
