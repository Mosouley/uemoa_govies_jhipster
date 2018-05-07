/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhispterTestModule } from '../../../test.module';
import { GoviesTypeComponent } from '../../../../../../main/webapp/app/entities/govies-type/govies-type.component';
import { GoviesTypeService } from '../../../../../../main/webapp/app/entities/govies-type/govies-type.service';
import { GoviesType } from '../../../../../../main/webapp/app/entities/govies-type/govies-type.model';

describe('Component Tests', () => {

    describe('GoviesType Management Component', () => {
        let comp: GoviesTypeComponent;
        let fixture: ComponentFixture<GoviesTypeComponent>;
        let service: GoviesTypeService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhispterTestModule],
                declarations: [GoviesTypeComponent],
                providers: [
                    GoviesTypeService
                ]
            })
            .overrideTemplate(GoviesTypeComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(GoviesTypeComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GoviesTypeService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new GoviesType(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.goviesTypes[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
