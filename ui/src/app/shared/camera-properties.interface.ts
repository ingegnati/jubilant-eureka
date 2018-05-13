import { ThreePoint } from './three-point.interface'

/**
 * A type that represents the common properties of a
 * Three.js camera
 */
export interface CameraProperties {
    position: ThreePoint,
    fov: number,
    nearPane: number,
    farPane: number
}
