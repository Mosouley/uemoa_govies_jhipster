import { BaseEntity } from './../../shared';

export const enum FrequenceCalcul {
    'ANNUAL',
    'SEMIANNUAL',
    'QUATERLY',
    'MONTHLY',
    'INFINE'
}

export const enum GoviesRepayMode {
    'AMORTCONST',
    'ANNUITCONST',
    'INFINE'
}

export class Issuance implements BaseEntity {
    constructor(
        public id?: number,
        public codeIsin?: string,
        public issueDate?: any,
        public issueReference?: string,
        public issueDescription?: string,
        public issueTranche?: string,
        public nominalValue?: number,
        public averageRate?: number,
        public couponRate?: number,
        public marginalRate?: number,
        public valueDate?: any,
        public maturityDate?: any,
        public interestPeriod?: FrequenceCalcul,
        public repayPeriod?: FrequenceCalcul,
        public repayMode?: GoviesRepayMode,
        public gracePeriod?: number,
        public bidAmount?: number,
        public getAmount?: number,
        public offeredAmount?: number,
        public offeredONC?: number,
        public minutesIssuance?: string,
        public issuer?: BaseEntity,
        public type?: BaseEntity,
    ) {
    }
}
