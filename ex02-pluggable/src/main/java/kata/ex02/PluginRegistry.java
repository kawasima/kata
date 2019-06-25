package kata.ex02;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.inject.Inject;
import kata.ex02.plugin.ApiSenderPlugin;
import kata.ex02.plugin.MailSenderPlugin;

import static kata.ex02.HookPoint.AFTER_ORDER_SAVE;

public class PluginRegistry {
    private Multimap<HookPoint, Plugin<?>> registry;

    @Inject
    public PluginRegistry(MailSenderPlugin mailSenderPlugin, ApiSenderPlugin apiSenderPlugin) {
        registry = Multimaps.synchronizedMultimap(ArrayListMultimap.create());
        register(AFTER_ORDER_SAVE, mailSenderPlugin);
        register(AFTER_ORDER_SAVE, apiSenderPlugin);
    }

    public void register(HookPoint hookPoint, Plugin<?> plugin) {
        registry.put(hookPoint, plugin);
    }

    private <V> void runPlugin(Plugin<V> plugin, Object arg) {
        plugin.run((V) arg);
    }

    public void runPlugins(HookPoint hookPoint, Object arg) {
        registry.get(hookPoint).forEach(plugin -> runPlugin(plugin, arg));
    }
}
