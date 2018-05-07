import { BaseEntity } from './../../shared';

export const enum ConventionBase {
    'REEL_30',
    'REEL_365',
    'TRENTE_REEL',
    'REEL_REEL',
    'REEL_364',
    'REEL_360'
}

export class GoviesType implements BaseEntity {
    constructor(
        public id?: number,
        public fullName?: string,
        public shortName?: string,
        public conventionBase?: ConventionBase,
        public issues?: BaseEntity[],
    ) {
    }
}
