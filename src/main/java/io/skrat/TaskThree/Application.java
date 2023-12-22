package io.skrat.TaskThree;


//MARK: - Base Interfaces
interface Button {
    void render();

    void onClick();
}

interface Checkbox {
    void render();

    Checkbox setState(boolean state);

    boolean getState();
}

interface Input {
    void render();

    Input setValue(String value);

    String getValue();
}

interface FormElementFactory {
    Button createButton();

    Checkbox createCheckbox();

    Input createInput();
}

//MARK: - Web classes family
class WebButton implements Button {
    public void render() {
        System.out.println("render WebButton");
    }

    public void onClick() {
        System.out.println("Event on click WebButton");
    }
}

class WebCheckbox implements Checkbox {
    private boolean state;

    public void render() {
        System.out.println(
                String.format("render WebCheckbox. With state %s", state)
        );
    }

    public boolean getState() {
        return state;
    }

    public Checkbox setState(boolean state) {
        this.state = state;
        return this;
    }
}

class WebInput implements Input {
    private String value;

    public Input setValue(String value) {
        this.value = value;
        return this;
    }

    public String getValue() {
        return value;
    }

    public void render() {
        System.out.println(
                String.format("render WebInput. With value %s", value)
        );
    }
}

class WebFormElementFactory implements FormElementFactory {
    public Button createButton() {
        return new WebButton();
    }

    public Checkbox createCheckbox() {
        return new WebCheckbox();
    }

    public Input createInput() {
        return new WebInput();
    }
}

//MARK: - Mob Classes Family
class MobButton implements Button {
    public void render() {
        System.out.println("render MobButton");
    }

    public void onClick() {
        System.out.println("Event on click MobButton");
    }
}

class MobCheckbox implements Checkbox {
    private boolean state;

    public void render() {
        System.out.println(
                String.format("render MobCheckbox. With state %s", state)
        );
    }

    public boolean getState() {
        return state;
    }

    public Checkbox setState(boolean state) {
        this.state = state;
        return this;
    }
}

class MobInput implements Input {
    private String value;

    public Input setValue(String value) {
        this.value = value;
        return this;
    }

    public String getValue() {
        return value;
    }

    public void render() {
        System.out.println(
                String.format("render MobInput. With value %s", value)
        );
    }
}

class MobFormElementFactory implements FormElementFactory {
    public Button createButton() {
        return new MobButton();
    }

    public Checkbox createCheckbox() {
        return new MobCheckbox();
    }

    public Input createInput() {
        return new MobInput();
    }
}


class Configuration {
    public final static int Web = 1;
    public final static int Mob = 2;

    private int platform;

    public int getPlatform() {
        return platform;
    }

    public Configuration setPlatfom(int platform) {
        this.platform = platform;
        return this;
    }

    public static Configuration initWeb() {
        return new Configuration().setPlatfom(Web);
    }

    public static Configuration initMob() {
        return new Configuration().setPlatfom(Mob);
    }
}

class AbstractFabricService extends Configuration {
    private FormElementFactory initFactory() {
        Configuration configuration = Configuration.initWeb();

        FormElementFactory factory;
        switch (configuration.getPlatform()) {
            case (Web):
                factory = new WebFormElementFactory();
                break;
            case (Mob):
                factory = new MobFormElementFactory();
                break;
            default:
                try {
                    throw new Exception("Неизвестный тип платформы");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
        }
        return factory;
    }

    private void renderForm(FormElementFactory factory) {
        Input input = factory.createInput();
        Button button = factory.createButton();

        input.setValue("Тестовое значение поля ввода данных");
        input.render();
        button.render();
    }

    public void exec() {
        renderForm(
                initFactory()
        );
    }
}

public class Application {
    public static void main(String[] args) {
        AbstractFabricService service = new AbstractFabricService();
        service.exec();
    }
}
