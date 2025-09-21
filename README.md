# Helix

Helix is a high-performance scripting engine for Minecraft designed to let users create complex scripts easily. Scripts can be created via in-game GUIs or by editing files using a Python-like syntax. Helix emphasizes speed, minimal memory overhead, and efficient execution.

## Features

- **Python-like scripting syntax**: Easy to read and write scripts.
- **High performance**: Avoids unnecessary object wrapping, boxing/unboxing, and memory overhead.
- **Scoped execution**: Scripts run in `ScriptLevel` or custom function scopes.
- **Triggers and events**: Precomputed singleton triggers with dynamically instantiated subscribers.
- **Asynchronous support**: Scripts can utilize `Future` for async evaluation.
- **Integration with Artifact plugin**: Scripts can be attached directly to item actions (`on-right-click`, `on-drop`, etc.).
- **Extensible**: Supports namespaces, type resolvers, type extensions, and dynamic methods/fields.
