export class StringUtil {
    public static isEmpty(str: string): boolean {
        return str === null || str === '' || str === undefined;
    }
    public static isNotEmpty(str: string): boolean {
        return !this.isEmpty(str);
    }
}
