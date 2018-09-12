export class Loading {
    public loading: boolean;
    constructor() {
        this.loading = false;
    }

    public start() {
        this.loading = true;
    }

    public stop() {
        this.loading = false;
    }


}
