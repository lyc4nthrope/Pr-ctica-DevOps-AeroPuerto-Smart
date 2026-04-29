# Skill Registry — Pr-ctica-DevOps-AeroPuerto-Smart

## User Skills

| Skill | Trigger |
|-------|---------|
| `branch-pr` | Creating a pull request, opening a PR, preparing changes for review |
| `issue-creation` | Creating a GitHub issue, reporting a bug, requesting a feature |
| `judgment-day` | "judgment day", "doble review", "juzgar", adversarial review |
| `skill-creator` | Creating a new skill, adding agent instructions |

> `go-testing` is present but NOT relevant — project uses Java, not Go.

## SDD Skills (orchestrator-managed)

| Skill | Phase |
|-------|-------|
| `sdd-explore` | Investigate ideas, read codebase |
| `sdd-propose` | Create change proposal |
| `sdd-spec` | Write specifications (Given/When/Then) |
| `sdd-design` | Technical design + architecture decisions |
| `sdd-tasks` | Break change into task checklist |
| `sdd-apply` | Implement tasks |
| `sdd-verify` | Validate implementation against specs |
| `sdd-archive` | Close change, persist final state |

## Compact Rules

### branch-pr
- Always open an issue first before creating a PR
- PR title: short, imperative, under 70 chars
- PR body: Summary (bullets) + Test plan checklist

### issue-creation
- Issue title: imperative verb + what (e.g. "Add Docker support")
- Include: context, expected behavior, acceptance criteria

### judgment-day
- Launch two independent blind judge agents simultaneously
- Synthesize findings, apply fixes, re-judge until both pass (max 2 iterations)

## Convention Files

- `~/.claude/CLAUDE.md` — global user rules (language, tone, philosophy, memory protocol)
