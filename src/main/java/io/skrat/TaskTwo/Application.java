package io.skrat.TaskTwo;

interface Button {
    void render();
}

interface Dialog {
    void render();
}

class WebButton implements Button {
    public void render() {
        System.out.println("render WebButton!");
    }
}

class MobButton implements Button {
    public void render() {
        System.out.println("render MobButton");
    }
}

abstract class AbstractDialog implements Dialog {
    //Fabric method
    abstract Button getButton();

    public void render() {
        Button button = getButton();
        button.render();
    }
}

class WebDialog extends AbstractDialog {
    public Button getButton() {
        return new WebButton();
    }
}

class MobDialog extends AbstractDialog {
    public Button getButton() {
        return new MobButton();
    }
}

class Configuration {
    public final static int Web = 1;
    public final static int Mob = 2;

    private int platform;

    public int getPlatform() {
        return platform;
    }

    public Configuration setPlatform(int platform) {
        this.platform = platform;
        return this;
    }

    public static Configuration initWeb() {
        return new Configuration().setPlatform(Web);
    }

    public static Configuration initMob() {
        return new Configuration().setPlatform(Mob);
    }

}

class FabricMethodService extends Configuration {
    public void exec() {
        Configuration configuration = Configuration.initWeb();
        //Configuration configuration = Configuration.initMob();
        Dialog dialog;
        switch (configuration.getPlatform()) {
            case (Web):
                dialog = new WebDialog();
                break;
            case (Mob):
                dialog = new MobDialog();
                break;
            default:
                try {
                    throw new Exception("Неизвестный тип платформы");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
        }
        dialog.render();
    }
}

public class Application {
    public static void main(String[] args) {
        FabricMethodService service = new FabricMethodService();
        service.exec();
    }
}
