/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhispterTestModule } from '../../../test.module';
import { IssuanceDialogComponent } from '../../../../../../main/webapp/app/entities/issuance/issuance-dialog.component';
import { IssuanceService } from '../../../../../../main/webapp/app/entities/issuance/issuance.service';
import { Issuance } from '../../../../../../main/webapp/app/entities/issuance/issuance.model';
import { IssuerService } from '../../../../../../main/webapp/app/entities/issuer';
import { GoviesTypeService } from '../../../../../../main/webapp/app/entities/govies-type';

describe('Component Tests', () => {

    describe('Issuance Management Dialog Component', () => {
        let comp: IssuanceDialogComponent;
        let fixture: ComponentFixture<IssuanceDialogComponent>;
        let service: IssuanceService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhispterTestModule],
                declarations: [IssuanceDialogComponent],
                providers: [
                    IssuerService,
                    GoviesTypeService,
                    IssuanceService
                ]
            })
            .overrideTemplate(IssuanceDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(IssuanceDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(IssuanceService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Issuance(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.issuance = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'issuanceListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Issuance();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.issuance = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'issuanceListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
