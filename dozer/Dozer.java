package dozer;

import dozer.command.CommandManager;
import dozer.event.EventBus;
import dozer.module.ModuleManager;
import dozer.setting.SettingManager;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Dozer {

  @Getter private static final Dozer singleton = new Dozer();
  private final String name = "DozerHack", version = "2.0.0";
  private final String[] authors = {"KillDozer", "Signam", "Shae", "Eternal"};

  @Setter
  private String prefix = ".";

  private final SettingManager settingManager = new SettingManager();
  private final ModuleManager moduleManager = new ModuleManager();
  private final CommandManager commandManager = new CommandManager();
  private final EventBus eventBus = new EventBus();

  public void init() {
    System.out.println("Initializing DozerHack...");

    moduleManager.init();
    commandManager.init();
    settingManager.init();

    System.out.println("DozerHack initialized!");
  }
}
