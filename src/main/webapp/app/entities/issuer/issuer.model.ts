import { BaseEntity } from './../../shared';

export class Issuer implements BaseEntity {
    constructor(
        public id?: number,
        public flagUrl?: string,
        public fullName?: string,
        public shortName?: string,
        public issues?: BaseEntity[],
    ) {
    }
}
