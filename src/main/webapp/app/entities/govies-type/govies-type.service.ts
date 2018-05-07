import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { GoviesType } from './govies-type.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<GoviesType>;

@Injectable()
export class GoviesTypeService {

    private resourceUrl =  SERVER_API_URL + 'api/govies-types';

    constructor(private http: HttpClient) { }

    create(goviesType: GoviesType): Observable<EntityResponseType> {
        const copy = this.convert(goviesType);
        return this.http.post<GoviesType>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(goviesType: GoviesType): Observable<EntityResponseType> {
        const copy = this.convert(goviesType);
        return this.http.put<GoviesType>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<GoviesType>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<GoviesType[]>> {
        const options = createRequestOption(req);
        return this.http.get<GoviesType[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<GoviesType[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: GoviesType = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<GoviesType[]>): HttpResponse<GoviesType[]> {
        const jsonResponse: GoviesType[] = res.body;
        const body: GoviesType[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to GoviesType.
     */
    private convertItemFromServer(goviesType: GoviesType): GoviesType {
        const copy: GoviesType = Object.assign({}, goviesType);
        return copy;
    }

    /**
     * Convert a GoviesType to a JSON which can be sent to the server.
     */
    private convert(goviesType: GoviesType): GoviesType {
        const copy: GoviesType = Object.assign({}, goviesType);
        return copy;
    }
}
