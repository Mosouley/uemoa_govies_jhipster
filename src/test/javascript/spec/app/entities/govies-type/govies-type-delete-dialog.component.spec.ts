/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhispterTestModule } from '../../../test.module';
import { GoviesTypeDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/govies-type/govies-type-delete-dialog.component';
import { GoviesTypeService } from '../../../../../../main/webapp/app/entities/govies-type/govies-type.service';

describe('Component Tests', () => {

    describe('GoviesType Management Delete Component', () => {
        let comp: GoviesTypeDeleteDialogComponent;
        let fixture: ComponentFixture<GoviesTypeDeleteDialogComponent>;
        let service: GoviesTypeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhispterTestModule],
                declarations: [GoviesTypeDeleteDialogComponent],
                providers: [
                    GoviesTypeService
                ]
            })
            .overrideTemplate(GoviesTypeDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(GoviesTypeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GoviesTypeService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
