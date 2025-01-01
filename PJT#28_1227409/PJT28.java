public class PJT28 {
    public static void main(String[] args){
		int year = 2024;
		int month = 12;
		int days = 0;

		switch (month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				days = 31;
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				days = 30;
				break;
			case 2: //// 윤년 계산: 4로 나누어 떨어지고 100으로 나누어 떨어지지 않거나, 400으로 나누어 떨어지는 해
				if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
					days = 29;
				} else {
					days = 28;
				}
				break;
		}

		System.out.println(days + "days for " + year + "-" + month);
    }
}

