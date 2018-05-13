
import * as THREE from 'three-full'

import { Component, OnInit, AfterViewInit, ViewChild, ElementRef } from '@angular/core'
import { AbstractThreeComponent } from '../../shared/three-component.abstract'
import { CameraProperties } from '../../shared/camera-properties.interface'

@Component({
  selector: 'app-meshes',
  templateUrl: './meshes.component.html',
  styleUrls: ['./meshes.component.scss']
})
export class MeshesComponent
  extends AbstractThreeComponent
  implements OnInit, AfterViewInit {
    @ViewChild('threeCanvas') canvasRef: ElementRef

  constructor() {
    super()
    // Add a color bg to the Scene
    this.addSceneBgColor = true
  }

  ngOnInit() {
  }

  ngAfterViewInit() {
    this.initThreeTemplate(
      this.canvasRef,
      this.getCameraProperties()
    )
  }

  private get canvas(): HTMLCanvasElement {
    return this.canvasRef.nativeElement
  }

  getCameraProperties(): CameraProperties {
    const aspRatio = this.canvas.clientWidth / this.canvas.clientHeight

    return {
      position: {x: 200, y: 200, z: 160},
      fov: 75,
      nearPane: 1,
      farPane: 10000,
      aspectRatio: aspRatio
    }

  }

  animateScene(): void {
    window.requestAnimationFrame(() => this.animateScene())
    const time = Date.now() * 0.0002
    this.camera.position.x = Math.sin( time ) * 250
    this.camera.position.z = Math.cos( time ) * 250
    this.camera.lookAt( this.scene.position )
    this.renderer.render(this.scene, this.camera)
  }

  addMeshes(): void {
    this.addPlane()
    this.addCilinder()
    this.addPolyField()
    this.addSprites()
  }

  /**
   * Adds a plane on the X axis
   */
  private addPlane(): void {
    const plane = new THREE.Mesh(
      new THREE.PlaneBufferGeometry( 200, 200 ),
      new THREE.MeshBasicMaterial( { color: Math.random() * 0xffffff, side: THREE.DoubleSide } )
    )
    plane.rotation.x = Math.PI / 2
    this.scene.add(plane)
  }

  /**
   * Defines a Cylinder object
   */
  private addCilinder(): void {
    const cylinder = new THREE.Mesh(
      new THREE.CylinderGeometry(
        10,             // Radius top
        40,            // Radius bottom
        40,            // Height
        25,             // Number of segments in circumference (i.e. how smooth)
        5,              // Number of segments high
        false           // Open ended
      ),
      new THREE.MeshBasicMaterial( {color: Math.random() * 0xffffff, opacity: 0.9, transparent: true } )
    )
    cylinder.position.y = 20
    cylinder.position.x = -80
    this.scene.add(cylinder)
  }

  /**
   * Adds triangles to the scene
   */
  private addPolyField(): void {
    const geometry = new THREE.Geometry()
    const material = new THREE.MeshBasicMaterial( { vertexColors: THREE.FaceColors, side: THREE.DoubleSide } )
    for ( let i = 0; i < 20; i ++ ) {
      const v = new THREE.Vector3(
        Math.random() * 1000 - 500,
        Math.random() * 1000 - 500,
        Math.random() * 1000 - 500
      )
      const v0 = new THREE.Vector3(
        Math.random() * 100 - 50,
        Math.random() * 100 - 50,
        Math.random() * 100 - 50
      )
      const v1 = new THREE.Vector3(
        Math.random() * 100 - 50,
        Math.random() * 100 - 50,
        Math.random() * 100 - 50
      )
      const v2 = new THREE.Vector3(
        Math.random() * 100 - 50,
        Math.random() * 100 - 50,
        Math.random() * 100 - 50
      )
      v0.add( v )
      v1.add( v )
      v2.add( v )
      const face = new THREE.Face3(
        geometry.vertices.push( v0 ) - 1,
        geometry.vertices.push( v1 ) - 1,
        geometry.vertices.push( v2 ) - 1,
        null,
        new THREE.Color( Math.random() * 0xffffff )
      )
      geometry.faces.push( face )
    }
    geometry.computeFaceNormals()
    // Group
    const group = new THREE.Mesh( geometry, material )
    group.scale.set( 0.2, 0.2, 0.2 )
    this.scene.add( group )
  }

  private addSprites(): void {
    for ( let i = 0; i < 5; i ++ ) {
      const material = new THREE.SpriteMaterial( { color: Math.random() * 0xffffff } )
      const sprite = new THREE.Sprite( material )
      sprite.position.x = Math.random() * 100 - 10
      sprite.position.y = Math.random() * 100 - 10
      sprite.position.z = Math.random() * 100 - 10
      sprite.scale.set(10, 10, 1)
      this.scene.add( sprite )
    }
  }

}
