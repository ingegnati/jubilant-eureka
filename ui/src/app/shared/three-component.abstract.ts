import { ThreePoint } from './three-point.interface'
import { CameraProperties } from './camera-properties.interface'
import * as THREE from 'three-full'
import { Camera } from 'three'
import { ElementRef } from '@angular/core'

/**
 * An abstract implementation of the essential THREE.js
 * operations to be extended by an Angular Component
 * in order to minimize the boilerplating.
 */
export abstract class AbstractThreeComponent {
    // Essential Three.js elements
    protected scene: THREE.Scene
    protected camera: THREE.PerspectiveCamera
    protected renderer: THREE.WebGLRenderer
    protected addSceneBgColor = false
    protected sceneBgColor = 0xE9ECEF
    /** @property Camera Properties */
    protected cp: CameraProperties

    constructor() {
        this.scene = new THREE.Scene()
    }

    /**
     * This is a template method that creates a THREE.js scene in
     * @param canvasRef {ElementRef} The ElementRef of the canvas to attach the WebGLRenderer to
     * @param pCameraProperties {CameraProperties} The THREE.js Camera Properties
     */
    protected initThreeTemplate(
        canvasRef: ElementRef,
        pCameraProperties?: CameraProperties
    ): void {
        this.cp = pCameraProperties !== null ? pCameraProperties : this.getDefaultCameraProperties()
        this.initScene()
        this.initCamera()
        this.addGridHelper()
        this.addOrbitControls(canvasRef)
        this.addMeshes()
        this.addLights()
        this.startRenderingLoop(canvasRef)
        this.animateScene()
    }

    abstract addMeshes(): void

    protected initScene(): void {
        if (this.addSceneBgColor) {
            this.scene.background = new THREE.Color( 0xE9ECEF )
        }
    }

    /**
     * Creates a THREE.Camera and sets its position
     * using the `cp` class property
     * It will then add the camera to the scene
     * α(radians) = α(degrees) × π / 180°
     */
    protected initCamera(): void {
        // Create camera
        this.camera = new THREE.PerspectiveCamera(
          this.cp.fov,
          this.cp.aspectRatio,
          this.cp.nearPane,
          this.cp.farPane,
        )
        this.camera.position.set(this.cp.position.x, this.cp.position.y, this.cp.position.z)
        this.scene.add(this.camera)
    }

    /**
     * Adds a X-Y GridHelper to the Scene
     */
    protected addGridHelper(): void {
        const xGrid = new THREE.GridHelper( 240, 10 )
        xGrid.rotation.x = Math.PI / 2
        this.scene.add(xGrid)
        const yGrid = new THREE.GridHelper( 240, 10 )
        this.scene.add(yGrid)
    }

    /**
     * Adds the OrbitControls to the Scene
     */
    protected addOrbitControls(pCanvasRef: ElementRef): void {
        const canvasEl = pCanvasRef.nativeElement
        const controls = new THREE.OrbitControls(this.camera, canvasEl)
        controls.screenSpacePanning = true
    }

    /**
     * Adds ambient, directional and point lights
     */
    protected addLights() {
        // const ambientLight = new THREE.AmbientLight(0x202020);
        const ambientLight = new THREE.AmbientLight(0x888888)
        this.scene.add(ambientLight)
        const directionalLight = new THREE.DirectionalLight(0xffffff, 0.75)
        directionalLight.position.set(0, 1, 1).normalize()
        this.scene.add(directionalLight)
        const pointLight = new THREE.PointLight(0xffffff, 5, 29)
        // pointLight.position.set( 0, -25, 10 )
        this.scene.add(pointLight)
    }

    private startRenderingLoop(pCanvasRef: ElementRef) {
        const canvasEl = pCanvasRef.nativeElement
        this.renderer = new THREE.WebGLRenderer({ antialias: true, alpha: true, canvas: canvasEl })
        this.renderer.setPixelRatio(devicePixelRatio)
        this.renderer.setSize(canvasEl.clientWidth, canvasEl.clientHeight)
        this.renderer.domElement.style.display = 'block'
        this.renderer.domElement.style.margin = 'auto'
    }

    protected animateScene(): void {
        window.requestAnimationFrame(() => this.animateScene())
        // const mouseX = 10;
        // const mouseY = 5;
        // this.camera.position.x += ( mouseX - this.camera.position.x ) * 0.05;
        // this.camera.position.y += ( - mouseY - this.camera.position.y ) * 0.05;
        // this.camera.lookAt( this.scene.position );
        this.renderer.render(this.scene, this.camera)
    }

    /**
     * Loads a default set of Camera properties
     */
    private getDefaultCameraProperties(): CameraProperties {
        return {
            position: {x: 0, y: 0, z: 200},
            fov: 35,
            nearPane: 0.1,
            farPane: 10000,
            aspectRatio: 1
        }
    }
}
