import * as THREE from 'three-full'
import { Component, OnInit, ViewChild, ElementRef, AfterViewInit } from '@angular/core'
import { AbstractThreeComponent } from '../shared/three-component.abstract'
import { CameraProperties } from '../shared/camera-properties.interface'

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

  constructor() {
    super()

  }

  ngOnInit() {}

  ngAfterViewInit() {
    this.initThreeTemplate(
      this.canvasRef,
      this.getCameraProperties()
    )
  }

  /**
   * This method requires the canvas to be ready in order
   * to calculate its aspectRatio.
   * It should be invoked after view init!
   */
  private getCameraProperties(): CameraProperties {
    const aspRatio = this.canvas.clientWidth / this.canvas.clientHeight

    return {
      position: {x: 200, y: 200, z: 260},
      fov: 50,
      nearPane: 0.1,
      farPane: 10000,
      aspectRatio: aspRatio
    }
  }

  private get canvas(): HTMLCanvasElement {
    return this.canvasRef.nativeElement
  }

  /* ***** THREE.JS MANAGEMENT ***** */

  addMeshes(): void {
      const geometry = new THREE.BoxGeometry(80, 80, 80)
      const material = new THREE.MeshBasicMaterial({color: 0x1C8ADB, wireframe: false})
      const cubeMesh = new THREE.Mesh(geometry, material)
      cubeMesh.position.set(0, 0, 0)
      this.scene.add(cubeMesh)
  }

}
