
 /* Created by Udit Jindal
 * Module: Terrain description
 * Created on: 10/11/2016*/

package terrain;
import com.jme3.asset.AssetManager;
import com.jme3.terrain.heightmap.AbstractHeightMap;
import com.jme3.terrain.geomipmap.TerrainQuad;
import com.jme3.terrain.heightmap.ImageBasedHeightMap;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;
import static com.jme3.bullet.PhysicsSpace.getPhysicsSpace;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;


 //*
 //* @author Ud-jindal
 
public class Terrain1{
    private Material mat_terrain;
    private TerrainQuad myterrain;
    private final String Name;
    private final int PatchSize;
    private final Vector3f LocalTrasnlation;
    private final Vector3f LocalScale;
    private final AssetManager assetManager;
    private AbstractHeightMap heightmap;
    
    public Terrain1(String Name, int PatchSize, Vector3f LocalTranslation, Vector3f LocalScale,
            AssetManager assetManager){
        
        
        this.assetManager= assetManager;
        this.Name= Name;
        this.PatchSize= PatchSize;
        this.LocalTrasnlation= LocalTranslation;
        this.LocalScale= LocalScale;
        //Setting Material
        init_material();
        

    }
    
    private void init_material(){
        mat_terrain = new Material(assetManager, 
            "Common/MatDefs/Terrain/Terrain.j3md");
        mat_terrain.setTexture("Alpha", assetManager.loadTexture(
            "Textures/Terrain/splat/alphamap.png")); 
        Texture grass = assetManager.loadTexture(
            "Textures/Terrain/splat/grass.jpg");
        grass.setWrap(WrapMode.Repeat);
        mat_terrain.setTexture("Tex1", grass);
        mat_terrain.setFloat("Tex1Scale", 64f);
        Texture dirt = assetManager.loadTexture(
            "Textures/Terrain/splat/dirt.jpg");
        dirt.setWrap(WrapMode.Repeat);
        mat_terrain.setTexture("Tex2", dirt);
        mat_terrain.setFloat("Tex2Scale", 32f);
        Texture rock = assetManager.loadTexture(
            "Textures/Terrain/splat/road.jpg");
        rock.setWrap(WrapMode.Repeat);
        mat_terrain.setTexture("Tex3", rock);
        mat_terrain.setFloat("Tex3Scale", 128f);
        heightmap = null;
        Texture heightMapImage = assetManager.loadTexture(
            "Textures/Terrain/splat/mountains512.png");
        heightmap = new ImageBasedHeightMap(heightMapImage.getImage());
        heightmap.load();
    }
    
    public void init_terrain()
    {
        myterrain= new TerrainQuad(Name, PatchSize, 513, heightmap.getHeightMap() );
        myterrain.setMaterial(mat_terrain);
        myterrain.setLocalTranslation(LocalTrasnlation);
        myterrain.setLocalScale(LocalScale);
        myterrain.addControl(new RigidBodyControl(0));
        getPhysicsSpace().add(myterrain);
    }
    
    public TerrainQuad get_TerrainQuad(){
        return myterrain;
    }
    
}