package fr.cpe.service;

import com.google.inject.Inject;
import fr.cpe.model.*;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Map;

public class GameService {

    // ── Palette ────────────────────────────────────────────────────────────────
    private static final String BG_DARK    = "#0d0d0f";
    private static final String BG_CARD    = "#16161a";
    private static final String GOLD       = "#c9a84c";
    private static final String GOLD_LIGHT = "#f0d080";
    private static final String CREAM      = "#f2ede4";
    private static final String MUTED      = "#6b6878";
    private static final String GREEN      = "#4caf7d";

    private final InventoryService inventoryService;
    private final ClickerService   clickerService;
    private final MaterialFactory  materialFactory;
    private final GameSession      session;

    private Text inventoryText;
    private VBox recipeListBox;
    private Pane gamePane;

    @Inject
    public GameService(InventoryService inventoryService,
                       ClickerService clickerService,
                       MaterialFactory materialFactory) {
        this.inventoryService = inventoryService;
        this.clickerService   = clickerService;
        this.materialFactory  = materialFactory;
        this.session          = GameSession.getInstance(new Player("Leegg", inventoryService));
    }

    public void init(Pane gamePane) {
        this.gamePane = gamePane;
        showMainView();
    }

    public void update(double width, double height) {}

    // ══════════════════════════════════════════════════════════════════════════
    //  VUE PRINCIPALE
    // ══════════════════════════════════════════════════════════════════════════
    private void showMainView() {
        gamePane.getChildren().clear();
        setBackground(gamePane);

        // ── Titre ─────────────────────────────────────────────────────────────
        Text titleMain = new Text("CLICKER");
        titleMain.setFont(Font.font("Georgia", FontWeight.BOLD, FontPosture.ITALIC, 52));
        titleMain.setFill(Color.web(GOLD));
        titleMain.setLayoutX(42);
        titleMain.setLayoutY(68);
        addGlowEffect(titleMain, GOLD, 18);

        Text titleSub = new Text("DESIGNA");
        titleSub.setFont(Font.font("Georgia", FontWeight.BOLD, 28));
        titleSub.setFill(Color.web(CREAM));
        titleSub.setLayoutX(46);
        titleSub.setLayoutY(96);

        Rectangle divider = new Rectangle(280, 1);
        divider.setFill(Color.web(GOLD, 0.45));
        divider.setLayoutX(42);
        divider.setLayoutY(108);

        // ── Clicker ───────────────────────────────────────────────────────────
        Clicker clicker = new Clicker(
                Ressource.FRENE, 1,
                new Image(getClass().getResourceAsStream("/frene.png"), 90, 90, true, true),
                new LoadClickStrategy(5,10,10)
        );

        ImageView clickerView = clicker.getImageView();
        clickerView.setOpacity(0.6);

        Text multiplierText = new Text("x" + clicker.getStrategy().getGainMultiplier());
        multiplierText.setFont(Font.font("Georgia", FontWeight.BOLD, 13));
        multiplierText.setFill(Color.web(GOLD_LIGHT));
        multiplierText.setLayoutX(100);
        multiplierText.setLayoutY(158);
        multiplierText.setMouseTransparent(true);

        clicker.onChange((s) -> {
            clickerView.setOpacity(s.canClick() ? 1.0 : 0.6);
            multiplierText.setText("x" + s.getGainMultiplier());
        });

        clickerView.setLayoutX(68);
        clickerView.setLayoutY(148);

        Rectangle halo = new Rectangle(110, 110);
        halo.setArcWidth(16); halo.setArcHeight(16);
        halo.setFill(Color.web(GOLD, 0.06));
        halo.setStroke(Color.web(GOLD, 0.25));
        halo.setStrokeWidth(1);
        halo.setLayoutX(58);
        halo.setLayoutY(140);
        halo.setMouseTransparent(true);

        Text clickerLabel = new Text("FRÊNE");
        clickerLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 13));
        clickerLabel.setFill(Color.web(GOLD_LIGHT));
        clickerLabel.setLayoutX(76);
        clickerLabel.setLayoutY(258);

        Text clickerSub = new Text("cliquez pour récolter");
        clickerSub.setFont(Font.font("Georgia", FontPosture.ITALIC, 11));
        clickerSub.setFill(Color.web(MUTED));
        clickerSub.setLayoutX(52);
        clickerSub.setLayoutY(273);

        clickerView.setOnMouseEntered(e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(120), clickerView);
            st.setToX(1.08); st.setToY(1.08); st.play();
        });
        clickerView.setOnMouseExited(e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(120), clickerView);
            st.setToX(1.0); st.setToY(1.0); st.play();
        });
        clickerView.setOnMouseClicked(e -> {
            clickerService.handleClick(clicker);
            refreshInventory();
            ScaleTransition st = new ScaleTransition(Duration.millis(80), clickerView);
            st.setToX(0.92); st.setToY(0.92);
            st.setOnFinished(ev -> {
                ScaleTransition back = new ScaleTransition(Duration.millis(80), clickerView);
                back.setToX(1.0); back.setToY(1.0); back.play();
            });
            st.play();
        });

        // ── Carte inventaire ──────────────────────────────────────────────────
        VBox inventoryCard = new VBox(6);
        inventoryCard.setLayoutX(230);
        inventoryCard.setLayoutY(136);
        inventoryCard.setPrefWidth(210);
        inventoryCard.setPadding(new Insets(14, 16, 14, 16));
        inventoryCard.setStyle(
                "-fx-background-color: " + BG_CARD + ";" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-color: " + GOLD + "44;" +
                        "-fx-border-radius: 10;" +
                        "-fx-border-width: 1;"
        );

        Text invTitle = new Text("▸ INVENTAIRE");
        invTitle.setFont(Font.font("Georgia", FontWeight.BOLD, 12));
        invTitle.setFill(Color.web(GOLD));

        inventoryText = new Text(buildInventoryString());
        inventoryText.setFont(Font.font("Courier New", 12));
        inventoryText.setFill(Color.web(CREAM, 0.85));
        inventoryCard.getChildren().addAll(invTitle, inventoryText);

        // ── Bouton recettes ───────────────────────────────────────────────────
        Button recipesBtn = goldButton("VOIR LES RECETTES  →");
        recipesBtn.setLayoutX(58);
        recipesBtn.setLayoutY(300);
        recipesBtn.setOnAction(e -> fadeTransition(() -> showRecipeView()));

        FadeTransition ft = new FadeTransition(Duration.millis(400), gamePane);
        ft.setFromValue(0); ft.setToValue(1); ft.play();

        gamePane.getChildren().addAll(
                halo, clickerView, multiplierText, clickerLabel, clickerSub,
                titleMain, titleSub, divider,
                inventoryCard, recipesBtn
        );
    }
    // ══════════════════════════════════════════════════════════════════════════
    //  VUE RECETTES
    // ══════════════════════════════════════════════════════════════════════════
    private void showRecipeView() {
        gamePane.getChildren().clear();
        setBackground(gamePane);

        Text title = new Text("ATELIER DE CRAFT");
        title.setFont(Font.font("Georgia", FontWeight.BOLD, FontPosture.ITALIC, 30));
        title.setFill(Color.web(GOLD));
        title.setLayoutX(24);
        title.setLayoutY(52);
        addGlowEffect(title, GOLD, 10);

        Text subtitle = new Text("CLICKER DESIGNA");
        subtitle.setFont(Font.font("Georgia", 13));
        subtitle.setFill(Color.web(MUTED));
        subtitle.setLayoutX(28);
        subtitle.setLayoutY(70);

        Rectangle divider = new Rectangle(440, 1);
        divider.setFill(Color.web(GOLD, 0.3));
        divider.setLayoutX(24);
        divider.setLayoutY(80);

        Button backBtn = mutedButton("← RETOUR");
        backBtn.setLayoutX(24);
        backBtn.setLayoutY(90);
        backBtn.setOnAction(e -> fadeTransition(() -> showMainView()));

        recipeListBox = new VBox(10);
        recipeListBox.setPadding(new Insets(6, 12, 12, 4));
        buildRecipeCards();

        ScrollPane scroll = new ScrollPane(recipeListBox);
        scroll.setLayoutX(20);
        scroll.setLayoutY(130);
        scroll.setPrefWidth(460);
        scroll.setPrefHeight(340);
        scroll.setFitToWidth(true);
        scroll.setStyle(
                "-fx-background: transparent;" +
                        "-fx-background-color: transparent;" +
                        "-fx-border-color: transparent;"
        );
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        FadeTransition ft = new FadeTransition(Duration.millis(350), gamePane);
        ft.setFromValue(0); ft.setToValue(1); ft.play();

        gamePane.getChildren().addAll(title, subtitle, divider, backBtn, scroll);
    }

    // ══════════════════════════════════════════════════════════════════════════
    //  CARTES RECETTES
    // ══════════════════════════════════════════════════════════════════════════
    private void buildRecipeCards() {
        recipeListBox.getChildren().clear();
        Map<Materials, Boolean> recipes = materialFactory.availableRecipies();

        for (MaterialRecipes recipe : MaterialRecipes.values()) {
            boolean canCraft = recipes.getOrDefault(recipe.getOutput(), false);

            HBox card = new HBox(14);
            card.setPadding(new Insets(12, 16, 12, 16));
            card.setPrefWidth(420);
            card.setAlignment(Pos.CENTER_LEFT);

            String cardStyleBase =
                    "-fx-background-color: " + (canCraft ? "#151f18" : BG_CARD) + ";" +
                            "-fx-background-radius: 10;" +
                            "-fx-border-color: " + (canCraft ? GREEN + "88" : GOLD + "22") + ";" +
                            "-fx-border-radius: 10;" +
                            "-fx-border-width: 1.2;";
            card.setStyle(cardStyleBase);

            Rectangle dot = new Rectangle(8, 8);
            dot.setArcWidth(8); dot.setArcHeight(8);
            dot.setFill(canCraft ? Color.web(GREEN) : Color.web(MUTED));

            VBox info = new VBox(3);
            HBox.setHgrow(info, Priority.ALWAYS);

            Text nameText = new Text(recipe.getOutput().name());
            nameText.setFont(Font.font("Georgia", FontWeight.BOLD, 14));
            nameText.setFill(canCraft ? Color.web(CREAM) : Color.web(MUTED));

            StringBuilder ingStr = new StringBuilder();
            recipe.getIngredients().forEach((res, qty) -> {
                int have = getAmount(res);
                ingStr.append(res.name()).append(" ×").append(qty)
                        .append("  (").append(have).append("/").append(qty).append(")   ");
            });
            Text ingText = new Text(ingStr.toString().trim());
            ingText.setFont(Font.font("Courier New", 11));
            ingText.setFill(Color.web(canCraft ? GREEN : MUTED));
            ingText.setWrappingWidth(270);
            info.getChildren().addAll(nameText, ingText);

            Button craftBtn = canCraft ? goldButton("CRAFT") : mutedButton("CRAFT");
            craftBtn.setDisable(!canCraft);
            craftBtn.setMinWidth(80);
            craftBtn.setOnAction(e -> {
                try {
                    materialFactory.create(recipe.getOutput());
                    buildRecipeCards();
                } catch (IllegalStateException ignored) {}
            });

            card.getChildren().addAll(dot, info, craftBtn);

            if (canCraft) {
                card.setOnMouseEntered(ev -> card.setStyle(
                        "-fx-background-color: #1a2e1f;" +
                                "-fx-background-radius: 10;" +
                                "-fx-border-color: " + GREEN + "cc;" +
                                "-fx-border-radius: 10;" +
                                "-fx-border-width: 1.2;"
                ));
                card.setOnMouseExited(ev -> card.setStyle(cardStyleBase));
            }

            recipeListBox.getChildren().add(card);
        }
    }

    // ══════════════════════════════════════════════════════════════════════════
    //  HELPERS
    // ══════════════════════════════════════════════════════════════════════════
    private void setBackground(Pane pane) {
        pane.setStyle("-fx-background-color: " + BG_DARK + ";");
        Rectangle vignette = new Rectangle();
        vignette.widthProperty().bind(pane.widthProperty());
        vignette.heightProperty().bind(pane.heightProperty());
        vignette.setFill(new LinearGradient(
                0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#c9a84c", 0.04)),
                new Stop(1, Color.web("#000000", 0.35))
        ));
        vignette.setMouseTransparent(true);
        pane.getChildren().add(vignette);
    }

    private void addGlowEffect(Text node, String hexColor, double radius) {
        DropShadow glow = new DropShadow();
        glow.setColor(Color.web(hexColor, 0.6));
        glow.setRadius(radius);
        glow.setSpread(0.1);
        node.setEffect(glow);
    }

    private Button goldButton(String label) {
        Button btn = new Button(label);
        String base =
                "-fx-background-color: transparent;" +
                        "-fx-border-color: " + GOLD + ";" +
                        "-fx-border-radius: 4;" +
                        "-fx-background-radius: 4;" +
                        "-fx-text-fill: " + GOLD_LIGHT + ";" +
                        "-fx-font-family: Georgia;" +
                        "-fx-font-size: 12;" +
                        "-fx-font-weight: bold;" +
                        "-fx-cursor: hand;" +
                        "-fx-padding: 6 16 6 16;";
        String hover =
                "-fx-background-color: " + GOLD + "22;" +
                        "-fx-border-color: " + GOLD_LIGHT + ";" +
                        "-fx-border-radius: 4;" +
                        "-fx-background-radius: 4;" +
                        "-fx-text-fill: " + GOLD_LIGHT + ";" +
                        "-fx-font-family: Georgia;" +
                        "-fx-font-size: 12;" +
                        "-fx-font-weight: bold;" +
                        "-fx-cursor: hand;" +
                        "-fx-padding: 6 16 6 16;";
        btn.setStyle(base);
        btn.setOnMouseEntered(e -> btn.setStyle(hover));
        btn.setOnMouseExited(e -> btn.setStyle(base));
        return btn;
    }

    private Button mutedButton(String label) {
        Button btn = new Button(label);
        String base =
                "-fx-background-color: transparent;" +
                        "-fx-border-color: " + MUTED + ";" +
                        "-fx-border-radius: 4;" +
                        "-fx-background-radius: 4;" +
                        "-fx-text-fill: " + MUTED + ";" +
                        "-fx-font-family: Georgia;" +
                        "-fx-font-size: 11;" +
                        "-fx-cursor: hand;" +
                        "-fx-padding: 5 12 5 12;";
        String hover =
                "-fx-background-color: " + MUTED + "18;" +
                        "-fx-border-color: " + CREAM + ";" +
                        "-fx-border-radius: 4;" +
                        "-fx-background-radius: 4;" +
                        "-fx-text-fill: " + CREAM + ";" +
                        "-fx-font-family: Georgia;" +
                        "-fx-font-size: 11;" +
                        "-fx-cursor: hand;" +
                        "-fx-padding: 5 12 5 12;";
        btn.setStyle(base);
        btn.setOnMouseEntered(e -> btn.setStyle(hover));
        btn.setOnMouseExited(e -> btn.setStyle(base));
        return btn;
    }

    private void fadeTransition(Runnable after) {
        FadeTransition ft = new FadeTransition(Duration.millis(200), gamePane);
        ft.setFromValue(1); ft.setToValue(0);
        ft.setOnFinished(e -> after.run());
        ft.play();
    }

    private void refreshInventory() {
        if (inventoryText != null) inventoryText.setText(buildInventoryString());
    }

    private String buildInventoryString() {
        StringBuilder sb = new StringBuilder();
        inventoryService.getInventory().forEach((item, qty) ->
                sb.append(item.toString()).append("  ×").append(qty).append("\n")
        );
        return sb.isEmpty() ? "(vide)" : sb.toString().trim();
    }

    private int getAmount(ICraftAble resource) {
        Integer val = inventoryService.getInventory().get(resource);
        return val == null ? 0 : val;
    }
}