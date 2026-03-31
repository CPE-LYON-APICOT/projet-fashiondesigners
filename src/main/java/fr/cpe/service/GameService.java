package fr.cpe.service;

// ╔══════════════════════════════════════════════════════════════════════════════╗
// ║                                                                            ║
// ║   ✏️  FICHIER MODIFIABLE — C'est le cœur de votre projet                   ║
// ║                                                                            ║
// ║   Le code actuel est un EXEMPLE (une balle qui rebondit).                  ║
// ║   Remplacez-le entièrement par votre propre logique de jeu.                ║
// ║                                                                            ║
// ║   Gardez juste la structure init() / update() car GameEngine              ║
// ║   les appelle automatiquement.                                             ║
// ║                                                                            ║
// ╚══════════════════════════════════════════════════════════════════════════════╝

import com.google.inject.Inject;
import fr.cpe.model.Clicker;
import fr.cpe.model.GameSession;
import fr.cpe.model.Player;
import fr.cpe.model.Ressource;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.Objects;

/**
 * Service de jeu — gère l'état du jeu et ses éléments visuels.
 *
 * <h2>C'est ici que vous codez votre jeu !</h2>
 *
 * <p>Ce fichier est un <strong>exemple</strong> : une balle qui rebondit.
 * Remplacez tout par votre propre logique.</p>
 *
 * <h2>Méthodes importantes :</h2>
 * <ul>
 *   <li>{@code init(gamePane)} — appelé une fois au démarrage, créez vos Nodes ici</li>
 *   <li>{@code update(width, height)} — appelé ~60x/sec, mettez à jour la logique et les positions ici</li>
 * </ul>
 *
 * <h2>Rendu (Scene Graph) :</h2>
 * <p>Pas besoin de méthode render() ! Vous créez des Nodes JavaFX (Circle, Rectangle,
 * Text, ImageView…) dans {@code init()}, vous les ajoutez au {@code gamePane},
 * et JavaFX les affiche automatiquement. Dans {@code update()}, vous mettez à jour
 * leurs positions.</p>
 *
 * <h2>Clics souris :</h2>
 * <p>Chaque Node gère ses propres clics :</p>
 * <pre>
 *   monCercle.setOnMouseClicked(e -&gt; {
 *       // ce cercle a été cliqué !
 *   });
 * </pre>
 *
 * <h2>Comment ajouter des dépendances :</h2>
 * <p>Ajoutez-les en paramètre du constructeur avec {@code @Inject} :</p>
 * <pre>
 *   @Inject
 *   public GameService(BallService ball, MonAutreService autre) {
 *       this.ball = ball;
 *       this.autre = autre;
 *   }
 * </pre>
 * <p>Guice les injectera automatiquement.</p>
 */
public class GameService {

    //Service à injecter
    GameSession session ;
    ClickerService clickerService;
    private final MachineService machineService;

    @Inject
    public GameService(InventoryService inventoryService, ClickerService clickerService,MachineService machineService) {
        session = GameSession.getInstance(new Player("Leegg", inventoryService));
        this.clickerService = clickerService;
        this.machineService = machineService;
    }

    /**
     * Initialise les éléments visuels du jeu (appelé une fois au démarrage).
     */
    public void init(Pane gamePane) {
        Player player = session.getPlayer();
        Clicker clicker = new Clicker(Ressource.FRENE, 1, new Image(getClass().getResourceAsStream("/frene.png"), 100, 100, true, true));

        Text inventoryText = new Text(player.showInventory());
        inventoryText.setFill(Color.web("#cdd6f4"));

        clicker.getImageView().setOnMouseClicked(e -> {
            clickerService.handleClick(clicker);
            inventoryText.setText(player.showInventory()); // refresh
        });

        gamePane.getChildren().addAll(clicker.getImageView(), inventoryText);

        gamePane.widthProperty().addListener((obs, oldVal, newVal) ->
                inventoryText.setX(newVal.doubleValue() - inventoryText.getBoundsInLocal().getWidth() - 20)
        );
        gamePane.heightProperty().addListener((obs, oldVal, newVal) ->
                inventoryText.setY(newVal.doubleValue() - 20)
        );
    }

    /**
     * Met à jour l'état du jeu (appelé à chaque frame).
     */
    public void update(double width, double height) {

    }
}
