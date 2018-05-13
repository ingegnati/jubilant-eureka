import { ThreePoint } from './three-point.interface'

/**
 * A type that represents the common properties of a
 * Three.js camera
 */
export interface CameraProperties {
    fov: number,
    nearPane: number,
    farPane: number
    aspectRatio: number,
    position: ThreePoint,
}
