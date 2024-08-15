package steve_gall.minecolonies_letsdo.module.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import steve_gall.minecolonies_letsdo.module.common.bakery.BakeryModule;
import steve_gall.minecolonies_letsdo.module.common.farm_and_charm.FarmAndCharmModule;

public class ModuleManager
{
	private static final List<OptionalModule<?>> _MODULES;
	public static final List<OptionalModule<?>> MODULES;
	private static boolean INITIALIZED;
	private static final List<OptionalModule<?>> _LOADED_MODULES;
	public static final List<OptionalModule<?>> LOADED_MODULES;

	static
	{
		_MODULES = new ArrayList<>();
		MODULES = Collections.unmodifiableList(_MODULES);

		_LOADED_MODULES = new ArrayList<>();
		LOADED_MODULES = Collections.unmodifiableList(_LOADED_MODULES);
	}

	public static final OptionalModule<FarmAndCharmModule> FARM_AND_CHARM = register("farm_and_charm", () -> FarmAndCharmModule::new);
	public static final OptionalModule<BakeryModule> BAKERY = register("bakery", LetsDoV2Module::new, () -> BakeryModule::new);

	private static <MODULE extends AbstractModule> OptionalModule<MODULE> register(String modid, Supplier<Supplier<MODULE>> initializer)
	{
		return register(modid, OptionalModule::new, initializer);
	}

	private static <WRAPPER extends OptionalModule<MODULE>, MODULE extends AbstractModule> WRAPPER register(String modid, BiFunction<String, Supplier<Supplier<MODULE>>, WRAPPER> wrapper, Supplier<Supplier<MODULE>> initializer)
	{
		var module = wrapper.apply(modid, initializer);
		_MODULES.add(module);
		return module;
	}

	public static boolean isInitialized()
	{
		return INITIALIZED;
	}

	public static void initialize()
	{
		if (INITIALIZED)
		{
			throw new IllegalCallerException();
		}

		INITIALIZED = true;
		_LOADED_MODULES.clear();

		for (var module : MODULES)
		{
			module.tryLoad();

			if (module.isLoaded())
			{
				_LOADED_MODULES.add(module);
			}

		}

	}

}
