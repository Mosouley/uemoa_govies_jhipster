/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhispterTestModule } from '../../../test.module';
import { IssuerComponent } from '../../../../../../main/webapp/app/entities/issuer/issuer.component';
import { IssuerService } from '../../../../../../main/webapp/app/entities/issuer/issuer.service';
import { Issuer } from '../../../../../../main/webapp/app/entities/issuer/issuer.model';

describe('Component Tests', () => {

    describe('Issuer Management Component', () => {
        let comp: IssuerComponent;
        let fixture: ComponentFixture<IssuerComponent>;
        let service: IssuerService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhispterTestModule],
                declarations: [IssuerComponent],
                providers: [
                    IssuerService
                ]
            })
            .overrideTemplate(IssuerComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(IssuerComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(IssuerService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Issuer(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.issuers[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
