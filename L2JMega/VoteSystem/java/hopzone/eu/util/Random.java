/*
 * Copyright (c) 2024 DenArt Designs
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package hopzone.eu.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author Nightwolf
 * iToPz Discord: https://discord.gg/KkPms6B5aE
 * @Author Rationale
 * Base structure credits goes on Rationale Discord: Rationale#7773
 * <p>
 * VDS Stands for: Vote Donation System
 * Script website: https://itopz.com/
 * Partner website: https://hopzone.eu/
 * Script version: 1.8
 * Pack Support: aCis 401
 * <p>
 * Freemium Donate Panel V4: https://www.denart-designs.com/
 * Download: https://mega.nz/folder/6oxUyaIJ#qQDUXeoXlPvBjbPMDYzu-g
 * Buy: https://shop.denart-designs.com/product/auto-donate-panel-v4/
 *
 * Quick Guide: https://github.com/nightw0lv/VDSystem/tree/master/Guide
 */
public final class Random
{
	public static int nextInt(int n)
	{
		return ThreadLocalRandom.current().nextInt(n);
	}

	public static int get(int n)
	{
		return nextInt(n);
	}

	public static int get(int min, int max)
	{
		return ThreadLocalRandom.current().nextInt(min, max == Integer.MAX_VALUE ? max : max + 1);
	}
}