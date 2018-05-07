/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhispterTestModule } from '../../../test.module';
import { GoviesTypeDetailComponent } from '../../../../../../main/webapp/app/entities/govies-type/govies-type-detail.component';
import { GoviesTypeService } from '../../../../../../main/webapp/app/entities/govies-type/govies-type.service';
import { GoviesType } from '../../../../../../main/webapp/app/entities/govies-type/govies-type.model';

describe('Component Tests', () => {

    describe('GoviesType Management Detail Component', () => {
        let comp: GoviesTypeDetailComponent;
        let fixture: ComponentFixture<GoviesTypeDetailComponent>;
        let service: GoviesTypeService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhispterTestModule],
                declarations: [GoviesTypeDetailComponent],
                providers: [
                    GoviesTypeService
                ]
            })
            .overrideTemplate(GoviesTypeDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(GoviesTypeDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GoviesTypeService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new GoviesType(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.goviesType).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
