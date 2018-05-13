import * as THREE from 'three-full'
import { Component, OnInit, ViewChild, ElementRef, AfterViewInit } from '@angular/core'
import { AbstractThreeComponent } from '../shared/three-component.abstract'

interface IThreePoint {
  x: number
  y: number
  z: number
}

interface ICameraProperties {
  position: IThreePoint,
  fov: number,
  nearPane: number,
  farPane: number
}

@Component({
  selector: 'app-logo',
  templateUrl: './logo.component.html',
  styleUrls: ['./logo.component.scss']
})
export class LogoComponent
    extends AbstractThreeComponent
    implements OnInit, AfterViewInit {
  @ViewChild('logoCanvas') canvasRef: ElementRef
  // Essential Three.js elements
  private camera: THREE.PerspectiveCamera
  private renderer: THREE.WebGLRenderer
  private scene: THREE.Scene
  private cp: ICameraProperties

  constructor() {
    super()
    this.scene = new THREE.Scene()
    // this.scene.background = new THREE.Color( 0xb0b0b0 )
  }

  ngOnInit() {
  }

  public methodToBeImplemented(): void {
    console.log('Hello from methodToBeImplemented')
  }

  public implementedMethod(): void {
    console.warn('The implemented is called from the subclass')
  }

  ngAfterViewInit() {
    this.initCamera()
    this.addGridHelper()
    this.loadSVG()
    this.addControls()
    this.addLights()
    this.startRenderingLoop()
    this.animateScene()
  }

  private get canvas(): HTMLCanvasElement {
    return this.canvasRef.nativeElement
  }

  /* ***** THREE.JS MANAGEMENT ***** */

  private initCameraProperties(): void {
    this.cp = {
      position: {x: 0, y: 0, z: 2000},
      fov: 50,
      nearPane: 0.1,
      farPane: 10000
    }
  }

  private initCamera(): void {
    this.initCameraProperties()
    const aspectRatio = this.canvas.clientWidth / this.canvas.clientHeight
    // Create camera
    this.camera = new THREE.PerspectiveCamera(
      this.cp.fov,
      aspectRatio,
      this.cp.nearPane,
      this.cp.farPane,
    )

    this.camera.position.set(this.cp.position.x, this.cp.position.y, this.cp.position.z)
    this.scene.add(this.camera)
  }

  private addGridHelper(): void {
    const helper = new THREE.GridHelper( 1560, 10 )
    helper.rotation.x = Math.PI / 2
    this.scene.add(helper)
    const helper2 = new THREE.GridHelper( 160, 10 )
    this.scene.add(helper2)
  }

  /**
   * Adds ambient, directional and point lights
   */
  private addLights() {
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

  private loadSVG(): void {
    const loader = new THREE.SVGLoader()
    // load a SVG resource
    loader.load('https://upload.wikimedia.org/wikipedia/commons/b/b0/NewTux.svg', (paths) => {
        const svo: THREE.SVGObject = new THREE.SVGObject(paths)
        console.log(svo)
        this.scene.add(svo)
      }, () => {
        console.log( 'Loading...' )
      }, (error) => console.error(error)
    )

      const geometry = new THREE.BoxGeometry(500, 500, 500)
      const material = new THREE.MeshBasicMaterial({color: 0xff0000, wireframe: false})
      const cubeMesh = new THREE.Mesh(geometry, material)
      const xPos = 500
      // cubeMesh.position.set(-xPos, 0, 0);
      // this.scene.add(cubeMesh)
  }

  private startRenderingLoop() {
    this.renderer = new THREE.WebGLRenderer({ antialias: true, alpha: true, canvas: this.canvas })
    this.renderer.setPixelRatio(devicePixelRatio)
    this.renderer.setSize(this.canvas.clientWidth, this.canvas.clientHeight)
    this.renderer.domElement.style.display = 'block'
    this.renderer.domElement.style.margin = 'auto'
  }

  private addControls(): void {
    const controls = new THREE.OrbitControls(this.camera, this.canvas)
    controls.screenSpacePanning = true
  }

  private animateScene(): void {
    window.requestAnimationFrame(() => this.animateScene())
    // const mouseX = 10;
    // const mouseY = 5;
    // this.camera.position.x += ( mouseX - this.camera.position.x ) * 0.05;
    // this.camera.position.y += ( - mouseY - this.camera.position.y ) * 0.05;
    // this.camera.lookAt( this.scene.position );
    this.renderer.render(this.scene, this.camera)
  }

}
