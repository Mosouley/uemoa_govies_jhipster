/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhispterTestModule } from '../../../test.module';
import { IssuanceDetailComponent } from '../../../../../../main/webapp/app/entities/issuance/issuance-detail.component';
import { IssuanceService } from '../../../../../../main/webapp/app/entities/issuance/issuance.service';
import { Issuance } from '../../../../../../main/webapp/app/entities/issuance/issuance.model';

describe('Component Tests', () => {

    describe('Issuance Management Detail Component', () => {
        let comp: IssuanceDetailComponent;
        let fixture: ComponentFixture<IssuanceDetailComponent>;
        let service: IssuanceService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhispterTestModule],
                declarations: [IssuanceDetailComponent],
                providers: [
                    IssuanceService
                ]
            })
            .overrideTemplate(IssuanceDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(IssuanceDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(IssuanceService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Issuance(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.issuance).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
