package rain_curtain;

import static processing.core.PApplet.cos;
import static processing.core.PApplet.sin;

public class CylindricalProjection implements Projection {

    private final float distance;
    private final float height;
    private final float radius;
    private final float[] xy;

    /**
     * Initialization of the projection.
     *
     * @param distance distance between cylinder and projector
     * @param height height of cylinder
     * @param radius radius cylinder
     */
    public CylindricalProjection(float distance, float height, float radius) {
        this.distance = distance;
        this.height = height;
        this.radius = radius;
        this.xy = new float[2];
    }

    @Override
    public float[] project(float x, float y) {
        float radius = computeY(y);
        float rho = computeX(x, radius);
        xy[0] = radius * cos(rho);
        xy[1] = radius * sin(rho);
        return xy;
    }

    private float computeX(float x, float projectedY) {
        return (x * projectedY) / radius;
    }

    private float computeY(float y) {
        return radius - (radius * y) / (distance + y);
    }
}
