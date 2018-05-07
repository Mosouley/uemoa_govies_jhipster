/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhispterTestModule } from '../../../test.module';
import { IssuerDetailComponent } from '../../../../../../main/webapp/app/entities/issuer/issuer-detail.component';
import { IssuerService } from '../../../../../../main/webapp/app/entities/issuer/issuer.service';
import { Issuer } from '../../../../../../main/webapp/app/entities/issuer/issuer.model';

describe('Component Tests', () => {

    describe('Issuer Management Detail Component', () => {
        let comp: IssuerDetailComponent;
        let fixture: ComponentFixture<IssuerDetailComponent>;
        let service: IssuerService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhispterTestModule],
                declarations: [IssuerDetailComponent],
                providers: [
                    IssuerService
                ]
            })
            .overrideTemplate(IssuerDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(IssuerDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(IssuerService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Issuer(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.issuer).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
