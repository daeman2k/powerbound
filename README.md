# powerbound
Powerbound is a Paper plugin implementing a strength & abilities system (damage scaling, per-weapon abilities, boss relics and a `/strength` GUI).

Development
-----------
- Java 17, Maven
- Config defaults are in `src/main/resources/powerbound/strength.yml` and `strength.json`.
- Run `mvn package` to build the plugin jar and place it in your Paper server `plugins/` folder.

Current status: core systems implemented (config loader, default configs, strength manager, damage scaling, cooldown manager, ability manager, relic manager, `/strength` GUI, commands and unit tests). Next steps: polish particle/sound effects, add integration tests, and refine ability/relic balancing.

Smoke testing
-------------
Run `mvn package`, then execute `./scripts/smoke-test.sh` to start a local Purpur/Paper server with the built plugin and do manual smoke checks (join server, test /strength, /ability, /claimrelic). The script attempts to download Purpur 1.21.11 by default if not present.

CI smoke job
------------
A GitHub Actions job `smoke` is included which will attempt to start a Purpur 1.21.11 server, wait for it to become available, run a `mcstatus` ping, and perform an RCON check if `mcrcon` is available on the runner. This is best-effort: runners vary and RCON may not be installed; the job will gracefully skip the RCON check if unavailable.


Commands
--------
- `/strength` — open the Strength GUI
- `/ability <tier>` — trigger the ability (light|medium|heavy|ultimate) for your held weapon
- `/claimrelic <key>` — claim a relic (warden|wither|dragon)
- `/claimrelic use` — use your claimed relic active ability (if owned and off cooldown)

Configuration
-------------
Defaults are in `src/main/resources/powerbound/strength.yml` and `strength.json`. Edit these to change damage numbers, strength scaling, GUI layout, and boss relic properties.

Build
-----
Run `mvn package` to build; note: Maven needs access to Papermc and Maven Central to resolve dependencies. This branch targets Purpur 1.21.11 (tested against Purpur-compatible Paper API 1.21.11). If your environment blocks external repositories, build may fail locally.

