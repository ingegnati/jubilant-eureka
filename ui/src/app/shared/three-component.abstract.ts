export abstract class AbstractThreeComponent {
    sharedName = 'This is a test from abstract'

    constructor() {
        this.methodToBeImplemented()
        this.implementedMethod()
    }

    abstract methodToBeImplemented(): void

    public implementedMethod(): void {
        console.log('Hello from implementedMethod')
    }

    private invisibleMethod(): void {
        const noAccess = 'go away'
    }
}
