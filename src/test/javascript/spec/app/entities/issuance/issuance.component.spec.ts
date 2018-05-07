/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhispterTestModule } from '../../../test.module';
import { IssuanceComponent } from '../../../../../../main/webapp/app/entities/issuance/issuance.component';
import { IssuanceService } from '../../../../../../main/webapp/app/entities/issuance/issuance.service';
import { Issuance } from '../../../../../../main/webapp/app/entities/issuance/issuance.model';

describe('Component Tests', () => {

    describe('Issuance Management Component', () => {
        let comp: IssuanceComponent;
        let fixture: ComponentFixture<IssuanceComponent>;
        let service: IssuanceService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhispterTestModule],
                declarations: [IssuanceComponent],
                providers: [
                    IssuanceService
                ]
            })
            .overrideTemplate(IssuanceComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(IssuanceComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(IssuanceService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Issuance(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.issuances[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
