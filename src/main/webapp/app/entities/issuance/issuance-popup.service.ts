import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { Issuance } from './issuance.model';
import { IssuanceService } from './issuance.service';

@Injectable()
export class IssuancePopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private issuanceService: IssuanceService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.issuanceService.find(id)
                    .subscribe((issuanceResponse: HttpResponse<Issuance>) => {
                        const issuance: Issuance = issuanceResponse.body;
                        if (issuance.issueDate) {
                            issuance.issueDate = {
                                year: issuance.issueDate.getFullYear(),
                                month: issuance.issueDate.getMonth() + 1,
                                day: issuance.issueDate.getDate()
                            };
                        }
                        if (issuance.valueDate) {
                            issuance.valueDate = {
                                year: issuance.valueDate.getFullYear(),
                                month: issuance.valueDate.getMonth() + 1,
                                day: issuance.valueDate.getDate()
                            };
                        }
                        if (issuance.maturityDate) {
                            issuance.maturityDate = {
                                year: issuance.maturityDate.getFullYear(),
                                month: issuance.maturityDate.getMonth() + 1,
                                day: issuance.maturityDate.getDate()
                            };
                        }
                        this.ngbModalRef = this.issuanceModalRef(component, issuance);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.issuanceModalRef(component, new Issuance());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    issuanceModalRef(component: Component, issuance: Issuance): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.issuance = issuance;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
