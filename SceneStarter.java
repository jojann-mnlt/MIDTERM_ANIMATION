public class SceneStarter {
  public static void main (String[] args) {
    SceneFrame scene =  new SceneFrame();
    scene.setUpGUI();
    scene.setUpSliderListeners();
    scene.setUpButtonListeners();
    scene.setUpMouseListeners();
  }
}
