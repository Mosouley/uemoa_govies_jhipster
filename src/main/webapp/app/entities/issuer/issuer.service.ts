import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Issuer } from './issuer.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Issuer>;

@Injectable()
export class IssuerService {

    private resourceUrl =  SERVER_API_URL + 'api/issuers';

    constructor(private http: HttpClient) { }

    create(issuer: Issuer): Observable<EntityResponseType> {
        const copy = this.convert(issuer);
        return this.http.post<Issuer>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(issuer: Issuer): Observable<EntityResponseType> {
        const copy = this.convert(issuer);
        return this.http.put<Issuer>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Issuer>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Issuer[]>> {
        const options = createRequestOption(req);
        return this.http.get<Issuer[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Issuer[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Issuer = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Issuer[]>): HttpResponse<Issuer[]> {
        const jsonResponse: Issuer[] = res.body;
        const body: Issuer[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Issuer.
     */
    private convertItemFromServer(issuer: Issuer): Issuer {
        const copy: Issuer = Object.assign({}, issuer);
        return copy;
    }

    /**
     * Convert a Issuer to a JSON which can be sent to the server.
     */
    private convert(issuer: Issuer): Issuer {
        const copy: Issuer = Object.assign({}, issuer);
        return copy;
    }
}
