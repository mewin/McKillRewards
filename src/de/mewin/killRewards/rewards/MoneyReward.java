/*
 * Copyright (C) 2013 mewin<mewin001@hotmail.de>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.mewin.killRewards.rewards;

import de.mewin.killRewards.VaultConnector;
import org.bukkit.entity.Player;

/**
 *
 * @author mewin<mewin001@hotmail.de>
 */
public class MoneyReward extends Reward
{
    private double amount;
    public MoneyReward(int kills, double amount, String pName)
    {
        super(kills, pName);
        this.amount = amount;
    }

    @Override
    public void give(Player player)
    {
        VaultConnector vault = VaultConnector.getInstance();
        if (vault.hasVault())
        {
            if (amount > 0)
            {
                vault.getEconomy().depositPlayer(player.getName(), amount);
            }
            else
            {
                vault.getEconomy().withdrawPlayer(player.getName(), Math.min(-amount, vault.getEconomy().getBalance(player.getName())));
            }
        }
    }
}